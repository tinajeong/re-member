export default function Loading() {
  return (
    <div role="status" class="w-screen animate-pulse p-4 flex flex-col justify-center items-center">
      <div class="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 w-96 mb-4"></div>
      <div class="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 w-96 mb-4"></div>
      <div class="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 w-96 mb-4"></div>
      <span class="sr-only">Loading...</span>
    </div>
  );
}
