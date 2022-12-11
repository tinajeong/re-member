import NavBar from "./component/NavBar";
import Post from "./component/Post";
import Tag from "./component/Tag";
import Error from "./component/Error";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className="App" class="flex flex-row">
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" element={<Post />}></Route>
          <Route path="/tag" element={<Tag />}></Route>
          <Route path="*" element={<Error/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
