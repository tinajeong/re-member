import { useForm } from "react-hook-form";
import { useQuery, useQueryClient, useMutation } from "react-query";
import Loading from "./Loading";
import Error from "./Error";
import { useLocation } from "react-router-dom";

const fetchPosts = async (keyword) => {
  var query = "http://localhost:8080/feed";
  if (keyword && keyword.length >= 2) {
    query += "/category?search=" + encodeURI(encodeURIComponent(keyword));
  }
  console.log(query);
  const res = await fetch(query);
  return res.json();
};

export default function Post() {
  const location = useLocation();
  var keyword ='';
  if (location && location.state && typeof location.state.tag === "string" && location.state.tag !== 0) {
    keyword = location.state.tag;
    console.log(keyword);
  }
  const { status, data: feed } = useQuery(["feed",keyword], () => fetchPosts(keyword), {
    onSuccess: (data) => {
      console.log(data);
    },
    onError: (e) => {
      console.log(e.message);
    },
    staleTime: 5000,
    cacheTime: Infinity,
  });
  const queryClient = useQueryClient();

  const mutation = useMutation(
    (newChunk) => {
      return fetch("http://localhost:8080/chunk", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newChunk),
      });
    },
    {
      onSuccess: (data, variables, context) => {
        queryClient.invalidateQueries("feed");
      },
    }
  );
  const { handleSubmit, register, reset } = useForm();

  if (status === "loading") return <Loading />;

  if (status === "error") return <Error />;

  function onSubmit(newChunk) {
    mutation.mutate(newChunk);

    reset({ title: "", contents: "", category: "" });
  }

  return (
    <div class="container flex flex-col p-8">
      <main class="grow items-center justify-center px-4">
        <form onSubmit={handleSubmit(onSubmit)}>
          <label htmlFor="title-input">
            <input
              id="title-input"
              rows="4"
              class=" p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              type="text"
              placeholder="제목"
              {...register("title")}
            />
          </label>
          <label htmlFor="content-input">
            <input
              id="content-input"
              type="text"
              class=" p-2.5 w-full h-24 text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="오늘의 생각을 적어볼까요!"
              {...register("contents")}
            />
          </label>
          <label htmlFor="category-input">
            <input
              id="category-input"
              class=" p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              type="text"
              placeholder="카테고리"
              {...register("category")}
            />
          </label>
          <button
            type="submit"
            class="text-white mt-2 bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2"
          >
            기록하기
          </button>
        </form>
      </main>
      <ul class="flex-1 px-4 py-8">
        {feed ? (
          feed.map((post) => (
            <li
              key={post.postId}
              class="p-6 bg-white border border-gray-200 rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700"
            >
              <h3 class="mb-2 text-xl font-semibold tracking-tight text-gray-900 dark:text-white">
                {post.title}
              </h3>
              <span class="inline-flex items-center text-blue-500 hover:underline">
                # {post.category}
              </span>
              <p class="mb-3 font-normal text-gray-500 dark:text-gray-400">
                {post.contents}
              </p>
            </li>
          ))
        ) : (
          <Loading />
        )}
      </ul>
    </div>
  );
}
