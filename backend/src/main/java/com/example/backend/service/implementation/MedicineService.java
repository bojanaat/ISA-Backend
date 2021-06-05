package com.example.backend.service.implementation;

import com.example.backend.dto.request.CreateMedRequest;
import com.example.backend.dto.request.UserRequest;
import com.example.backend.dto.response.DermatologistResponse;
import com.example.backend.dto.response.MedicineResponse;
import com.example.backend.dto.response.PharmacistResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.Dermatologist;
import com.example.backend.model.Medicine;
import com.example.backend.model.User;
import com.example.backend.repository.IMedicineRepository;
import com.example.backend.service.IMedicineService;
import com.example.backend.utils.MedShape;
import com.example.backend.utils.MedicineType;
import com.example.backend.utils.UserType;
import org.springframework.stereotype.Service;

@Service
public class MedicineService implements IMedicineService {

    private final IMedicineRepository _iMedicineRepository;

    public MedicineService(IMedicineRepository iMedicineRepository) {
        _iMedicineRepository = iMedicineRepository;
    }

    @Override
    public MedicineResponse createMed(CreateMedRequest request) {
        Medicine medicine = new Medicine();
        System.out.println(request.getMedicineType());
        if(request.getMedicineType().equals("ANTIBIOTIC")){
            medicine.setMedicineType(MedicineType.ANTIBIOTIC);
        }else if(request.getMedicineType().equals("ANESTHETIC")){
            medicine.setMedicineType(MedicineType.ANESTHETIC);
        }else if(request.getMedicineType().equals("ANTIHISTAMINE")){
            medicine.setMedicineType(MedicineType.ANTIHISTAMINE);
        }else if(request.getMedicineType().equals("ANTIFUNGAL")){
            medicine.setMedicineType(MedicineType.ANTIFUNGAL);
        }else if(request.getMedicineType().equals("ANALGETIC")){
            medicine.setMedicineType(MedicineType.ANALGETIC);
        }

        if(request.getMedShape().equals("POWDER")){
            medicine.setMedShape(MedShape.POWDER);
        }else if(request.getMedShape().equals("CAPSULE")){
            medicine.setMedShape(MedShape.CAPSULE);
        }else if(request.getMedShape().equals("TABLET")){
            medicine.setMedShape(MedShape.TABLET);
        }else if(request.getMedShape().equals("GEL")){
            medicine.setMedShape(MedShape.GEL);
        }else if(request.getMedShape().equals("SYRUP")){
            medicine.setMedShape(MedShape.SYRUP);
        }else if(request.getMedShape().equals("SOLUTION")){
            medicine.setMedShape(MedShape.SOLUTION);
        }


        medicine.setCode(request.getCode());
        medicine.setIngredients(request.getIngredients());
        medicine.setManufacturer(request.getManufacturer());
        medicine.setNotes(request.getNotes());
        medicine.setRecipe(request.isRecipe());
        medicine.setName(request.getName());
        medicine.setReplacementCode(request.getReplacementCode());

        Medicine savedMedicine = _iMedicineRepository.save(medicine);

        return mapToResponse(savedMedicine);
    }

    private MedicineResponse mapToResponse(Medicine savedMedicine) {

        MedicineResponse medicineResponse = new MedicineResponse();
        medicineResponse.setMedicineType(savedMedicine.getMedicineType());
        medicineResponse.setId(savedMedicine.getId());
        medicineResponse.setManufacturer(savedMedicine.getManufacturer());
        medicineResponse.setCode(savedMedicine.getCode());
        medicineResponse.setRecipe(savedMedicine.isRecipe());
        medicineResponse.setReplacementCode(savedMedicine.getReplacementCode());
        medicineResponse.setMedShape(savedMedicine.getMedShape());
        medicineResponse.setIngredients(savedMedicine.getIngredients());
        medicineResponse.setNotes(savedMedicine.getNotes());
        medicineResponse.setName(savedMedicine.getName());
        return medicineResponse;
    }
}
