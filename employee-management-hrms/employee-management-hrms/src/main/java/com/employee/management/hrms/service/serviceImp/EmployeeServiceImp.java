import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employee.management.hrms.dto.EmployeeDTO;
import com.employee.management.hrms.entity.Department;
import com.employee.management.hrms.entity.Employee;
import com.employee.management.hrms.repository.DepartmentRepository;
import com.employee.management.hrms.repository.EmployeeRepository;
import com.employee.management.hrms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {

        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee manager = null;
        if (dto.getManagerId() != null) {
            manager = employeeRepository.findById(dto.getManagerId()).orElse(null);
        }

        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setPhone(dto.getPhone());
        emp.setDesignation(dto.getDesignation());
        emp.setJoiningDate(dto.getJoiningDate());
        emp.setDepartment(dept);
        emp.setManager(manager);

        Employee saved = employeeRepository.save(emp);

        return mapToDTO(saved);
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapToDTO(emp);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto) {

        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setName(dto.getName());
        emp.setPhone(dto.getPhone());
        emp.setDesignation(dto.getDesignation());

        Employee updated = employeeRepository.save(emp);
        return mapToDTO(updated);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO mapToDTO(Employee emp) {

        Integer managerId = emp.getManager() != null ?
                emp.getManager().getEmployeeId() : null;

        return new EmployeeDTO(emp.getEmployeeId(),emp.getName(),emp.getEmail(), emp.getPhone(),emp.getDesignation(),emp.getJoiningDate(),emp.getDepartment().getDepartmentId(),managerId );
    }
}