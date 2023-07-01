import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

val client = OkHttpClient()

val mediaType = MediaType.parse("application/json")
val apiKey = "YOUR_OPENAI_API_KEY"
val model = "gpt-3.5-turbo"

val chatMessage = JSONObject()
  .put("role", "name of choice")
  .put("content", "Your content here.")

val userMessage = JSONObject()
  .put("role", "user")
  .put("content", "User message")

val messages = JSONArray()
  .put(chatMessage)
  .put(userMessage)

val bodyContent = JSONObject()
  .put("model", model)
  .put("messages", messages)

val body = RequestBody.create(mediaType, bodyContent.toString())
val request = Request.Builder()
  .url("https://api.openai.com/v1/chat/completions")
  .post(body)
  .addHeader("Content-Type", "application/json")
  .addHeader("Authorization", "Bearer $apiKey")
  .build()

val response = client.newCall(request).execute()
val responseData = JSONObject(response.body()?.string())

val replyMessage = responseData.getJSONArray("choices").getJSONObject(0).getString("content").trim()
