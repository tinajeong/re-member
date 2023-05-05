export default function Loading() {
  return (
    <div
      role="status"
      className="w-screen animate-pulse p-4 flex flex-col justify-start items-start"
    >
      {[48, 96, 48].map((width, index) => (
        <div
          key={index}
          className="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 mb-4"
          style={{ width }}
        ></div>
      ))}
      <div className="h-2.5"></div>
      <span className="sr-only">Loading...</span>
    </div>
  );
}
