import { Link } from "react-router-dom";
import {
  HouseFill,
  Hash,
  BookmarkFill,
  DoorClosedFill,
} from "react-bootstrap-icons";
export default function NavBar() {
  return (
    <nav class="bg-blue-500 w-96 h-screen sticky top-0">
      <div class="container flex flex-col p-8 m-2 text-gray-200">
        <div class="text-2xl">
          <div class="p-2.5 mt-1 flex items-center">
            <span class="text-[15px] ml-2 font-bold">Re-</span>
          </div>
        </div>

        <Link to="/" class="p-2.5 mt-2 flex items-center rounded-md">
          <HouseFill></HouseFill>
          <span class="text-[15px] ml-2 font-bold">내려읽기</span>
        </Link>

        <Link to="/tag" class="p-2.5 mt-2 flex items-center rounded-md">
          <Hash></Hash>
          <span class="text-[15px] ml-2 font-bold">탐색하기</span>
        </Link>

        <Link to="/bookmark" class="p-2.5 mt-2 flex items-center rounded-md">
          <BookmarkFill></BookmarkFill>
          <span class="text-[15px] ml-2 font-bold">북마크</span>
        </Link>

        <Link to="/logout" class="p-2.5 mt-2 flex items-center rounded-md">
          <DoorClosedFill></DoorClosedFill>
          <span class="text-[15px] ml-2 font-bold">로그아웃</span>
        </Link>
      </div>
    </nav>
  );
}
