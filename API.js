import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';

export default function App() {
  const [response, setResponse] = useState(null);

  useEffect(() => {
    const apiUrl = 'https://api.openai.com/v1/chat/completions';
    const apiKey = 'YOUR_OPENAI_API_KEY';
    const model = 'gpt-3.5-turbo';
    const chatMessage = [
      {
        role: 'name of choice',
        content: 'Your content here.',
      },
      {
        role: 'user',
        content: 'User message',
      },
    ];

    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${apiKey}`,
      },
      body: JSON.stringify({
        model,
        messages: chatMessage,
      }),
    })
      .then((response) => response.json())
      .then((json) => {
        const responseData = json;
        const replyMessage = responseData.choices[0].content.trim();
        setResponse(replyMessage);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }, []);

  return (
    <View>
      <Text>{response}</Text>
    </View>
  );
}
