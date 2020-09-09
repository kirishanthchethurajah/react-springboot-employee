import React from "react";
import ListEmployeeComponent from "./components/ListEmployeeComponent";
import "./App.css";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent";
import CreateEmployeeComponent from "./components/CreateEmployeeComponent";

// import UpdateEmployeeComponent from "./components/UpdateEmployeeComponent";
import ViewEmployeeComponent from "./components/ViewEmployeeComponent";
function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className="container">
          <Switch>
            <Route path="/" exact component={ListEmployeeComponent}></Route>
            <Route path="/employees" component={ListEmployeeComponent}></Route>
            <Route
              path="/add-employee/:id"
              component={CreateEmployeeComponent}
            ></Route>
            <Route
              path="/view-employee/:id"
              component={ViewEmployeeComponent}
            ></Route>

            {/* <Route
              path="/update-employee/:id"
              component={UpdateEmployeeComponent}
            ></Route> */}
          </Switch>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
