import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        Main main = new Main();
        Map<String, List<Employee>> map = new HashMap<>();
        Sheet sheet = WorkbookFactory.create(main.readFile()).getSheet("Sheet1");
        Iterator<Row> rowIterator = sheet.rowIterator();
        int rowNo = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if(rowNo == 0){
                rowNo += 1;
                continue;
            }

            //iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            int col = 0;
            Employee employee = new Employee();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(col == 0 && cell != null && !cell.toString().isBlank()){
                    employee.setPositionId(cell.toString());
                }
                if(col == 1 && cell != null && !cell.toString().isBlank()){
                    employee.setStatus(cell.toString());
                }
                if(col == 2 && cell != null && !cell.toString().isBlank()){
                    employee.setInTime(new Date(cell.toString()));
                }
                if(col == 3 && cell != null && !cell.toString().isBlank()){
                    employee.setOutTime(new Date(cell.toString()));
                }
                if(col == 4 && cell != null && !cell.toString().isBlank()){
                    employee.setTimeCardHours(cell.toString());
                }
                if(col == 5 && cell != null && !cell.toString().isBlank()){
                    employee.setPayStart(new Date(cell.toString()));
                }
                if(col == 6 && cell != null && !cell.toString().isBlank()){
                    employee.setPayEnd(new Date(cell.toString()));
                }
                if(col == 7 && cell != null && !cell.toString().isBlank()){
                    employee.setName(cell.toString());
                }
                if(col == 8 && cell != null && !cell.toString().isBlank()){
                    employee.setFileNumber(cell.toString());
                }
                col += 1;
            }
            if(map.containsKey(employee.getPositionId())){
                List<Employee> employees = new ArrayList<>(map.get(employee.getPositionId()));
                employees.add(employee);
                map.put(employee.getPositionId(), employees);
            } else{
                map.put(employee.getPositionId(), List.of(employee));
            }
        }

        List<Employee> moreThan14Hours = new ArrayList<>();
        Set<String> consecutiveDays = new HashSet<>();
        Set<String> shiftTime = new HashSet<>();
        for (Map.Entry<String, List<Employee>> emp : map.entrySet()){
            List<Employee> value = new ArrayList<>(emp.getValue());
            if(!emp.getValue().isEmpty()){
                value.sort(Comparator.comparing(Employee::getInTime));
            }

            for(int i = 0; i < value.size(); i++) {
                if(i < value.size()-1 && (value.get(i).getOutTime()!=null && value.get(i+1).getInTime()!=null) && ((value.get(i+1).getInTime().getTime() - value.get(i).getOutTime().getTime()) / 1000) >= 3600 &&
                        ((value.get(i+1).getInTime().getTime() - value.get(i).getOutTime().getTime()) / 1000) < 36000){
                    shiftTime.add(value.get(i).getPositionId());
                }
            }

            int count = 0;
            for(int i = 0; i < value.size(); i++){
                if(i < value.size()-1 && value.get(i).getInTime().compareTo(value.get(i+1).getInTime()) > 0){
                    count++;
                } else {
                    count = 0;
                }
                if(count == 7){
                    consecutiveDays.add(value.get(i).getPositionId());
                }
            }
            moreThan14Hours = emp.getValue().stream().filter(employee -> (employee.getOutTime()!=null && employee.getInTime()!=null) && ((employee.getOutTime().getTime() - employee.getInTime().getTime()) / 1000) >= 50400)
                    .collect(Collectors.toList());
        }

        consecutiveDays.forEach(st-> {
            System.out.println("Name: " + map.get(st).get(0).getName() + ", PositionId: "+ map.get(st).get(0).getPositionId()); ;
        });

        shiftTime.forEach(st-> {
            System.out.println("Name: " + map.get(st).get(0).getName() + ", PositionId: "+ map.get(st).get(0).getPositionId()); ;
        });

        for (Employee emp : moreThan14Hours){
            System.out.println("Name: " + emp.getName() + ", PositionId: " + emp.getPositionId());
        }

    }

    private FileInputStream readFile() throws FileNotFoundException {
        return new FileInputStream("src\\main\\java\\resource\\test.xlsx");
    }
}