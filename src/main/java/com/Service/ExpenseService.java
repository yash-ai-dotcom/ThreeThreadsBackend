package com.Service;

import com.Entity.Expense;
import com.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAllByOrderByExpenseDateDesc();
    }

    public Expense saveExpense(Expense expense) {
        if (expense.getAmount() == null || expense.getAmount() <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than zero.");
        }
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedData) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense record not found: " + id));

        existing.setCategory(updatedData.getCategory());
        existing.setSubCategory(updatedData.getSubCategory());
        existing.setAmount(updatedData.getAmount());
        existing.setExpenseDate(updatedData.getExpenseDate());
        existing.setPaymentMethod(updatedData.getPaymentMethod());
        existing.setVendorName(updatedData.getVendorName());
        existing.setNotes(updatedData.getNotes());

        return expenseRepository.save(existing);
    }

    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}