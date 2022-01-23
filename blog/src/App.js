import { useState } from 'react'
import './mvp.css'
import { useForm } from 'react-hook-form'
import { useQuery, useQueryClient, useMutation } from 'react-query'
// feed라는 것은... 결국에는 뭔가의 목록
// https://react-hook-form.com/

// npm install react-hook-form
// npm i react-hook-form
// npm i react-query

function App () {
  // https://react-query.tanstack.com/overview
  // https://dev-pengun.tistory.com/entry/Spring-Boot-CORS-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
  const { isLoading, error, data: feed } = useQuery('feed', () =>
    fetch('http://localhost:8080/feed').then(res => res.json())
  )
  const queryClient = useQueryClient()

  const mutation = useMutation(
    newChunk => {
      return fetch('http://localhost:8080/chunk', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newChunk)
      })
    },
    {
      onSuccess: (data, variables, context) => {
        // 캐시 무효화! 다시 요청을 받아옴!
        queryClient.invalidateQueries('feed')
      }
    }
  )
  const { handleSubmit, register, reset } = useForm()

  if (isLoading) return 'Loading...'

  if (error) return 'An error has occurred: ' + error.message

  // 구조분해할당
  // https://ko.javascript.info/destructuring-assignment

  function onSubmit (newChunk) {
    mutation.mutate(newChunk)

    reset({ title: '', contents: '', category: '' })
  }

  return (
    <section>
      <h1>테스트</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label htmlFor='title-input'>
          제목
          <input id='title-input' type='text' {...register('title')} />
        </label>
        <label htmlFor='content-input'>
          내용
          <input id='content-input' type='text' {...register('contents')} />
        </label>
        <label htmlFor='category-input'>
          카테고리
          <input id='category-input' type='text' {...register('category')} />
        </label>
        <button type='submit'>작성하기 </button>
      </form>
      <ul>
        {feed.map(post => (
          <li key={post.contents}>
            <h3>{post.title}</h3>
            <span>{post.category}</span>
            <p>{post.contents}</p>
          </li>
        ))}
      </ul>
    </section>
  )
}

export default App
