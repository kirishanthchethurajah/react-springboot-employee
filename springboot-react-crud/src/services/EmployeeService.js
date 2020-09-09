import axios from "axios";
const EMPLOYEE_URL = "http://localhost:8080/api/v1/employees";
class EmployeeService {
  getEmployees() {
    return axios.get(EMPLOYEE_URL);
  }

  createEmployee(employee) {
    return axios.post(EMPLOYEE_URL, employee);
  }

  updateEmployee(employee, empId) {
    return axios.put(`${EMPLOYEE_URL}/${empId}`, employee);
  }

  getEmployeeById(empId) {
    return axios.get(`${EMPLOYEE_URL}/${empId}`);
  }

  deleteEmployeeById(empId) {
    return axios.delete(`${EMPLOYEE_URL}/${empId}`);
  }
}

export default new EmployeeService();
