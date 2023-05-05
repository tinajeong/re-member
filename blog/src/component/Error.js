export default function Error(props) {
  console.log(props);
  return (
    <div
      role="status"
      class="container w-screen animate-pulse p-8 flex flex-col justify-center items-center"
    >
      {typeof props.message === "string" && props.message.length !== 0 ? (
        <div class="text-2xl">{props.message}</div>
      ) : (
        <div class="text-2xl">이런! 에러가 발생했습니다.</div>
      )}
      <span class="sr-only">Error...</span>
    </div>
  );
}
