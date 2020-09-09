import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

class ListEmployeeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { employees: [] };
    this.addEmployee = this.addEmployee.bind(this);
    this.editEmployee = this.editEmployee.bind(this);
    this.deleteEmployee = this.deleteEmployee.bind(this);
  }
  componentDidMount() {
    EmployeeService.getEmployees().then((res) => {
      this.setState({ employees: res.data });
    });
  }
  addEmployee() {
    this.props.history.push("/add-employee/_add");
  }
  editEmployee(id) {
    this.props.history.push(`/add-employee/${id}`);
  }
  deleteEmployee(id) {
    EmployeeService.deleteEmployeeById(id).then((res) => {
      this.setState({
        employees: this.state.employees.filter(
          (employee) => employee.empId !== id
        ),
      });
    });
  }

  viewEmployee(id) {
    this.props.history.push(`/view-employee/${id}`);
  }

  render() {
    return (
      <div>
        <h2 className="text-center">Employees List</h2>
        <div className="row">
          <button className="btn btn-primary" onClick={this.addEmployee}>
            Add Employee
          </button>
        </div>
        <br></br>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Employee First Name</th>
                <th>Employee Last Name</th>
                <th>Employee Email Id</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {this.state.employees.map((emp) => (
                <tr key={emp.empId}>
                  <td>{emp.firstName}</td>
                  <td>{emp.lastName}</td>
                  <td>{emp.emailId}</td>
                  <td>
                    <button
                      className="btn btn-info"
                      onClick={() => this.editEmployee(emp.empId)}
                    >
                      Update
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      className="btn btn-danger"
                      onClick={() => this.deleteEmployee(emp.empId)}
                    >
                      delete
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      className="btn btn-info"
                      onClick={() => this.viewEmployee(emp.empId)}
                    >
                      View
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListEmployeeComponent;
