import './App.css'
import HeaderMenu from "./HeaderMenu.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SearchUser from "./SearchUser.tsx";
import SearchCar from "./SearchCar.tsx";
import UserCarTable from "./Table.tsx";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <HeaderMenu/>
        <Routes>
          <Route path="/" element={<UserCarTable/>}/>
          <Route path="home" element={<UserCarTable/>}/>
          <Route path="search-user" element={<SearchUser/>}/>
          <Route path="search-car" element={<SearchCar/>}/>
          <Route path="*" element={<UserCarTable/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
