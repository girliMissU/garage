package com.garage.admin.service;

import com.garage.admin.dao.SolutionDAO;
import com.garage.admin.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionsService {
    @Autowired
    SolutionDAO solutionDAO;

    public int addSolution(Solution solution){
        return solutionDAO.addSolution(solution) > 0 ? solution.getId() : 0;
    }
}
