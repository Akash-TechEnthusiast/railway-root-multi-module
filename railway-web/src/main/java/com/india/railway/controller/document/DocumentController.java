package com.india.railway.controller.document;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.mysql.InvoiceItem;

import java.util.List;

@Controller
@RequestMapping("/api")
public class DocumentController {

    @GetMapping("/invoice")
    public String getInvoice(Model model) {
        List<InvoiceItem> items = List.of(
                new InvoiceItem("Tuition Fee (May)", 300.00),
                new InvoiceItem("Library Fee", 25.00),
                new InvoiceItem("Lab Fee", 40.00));

        double total = items.stream().mapToDouble(InvoiceItem::getAmount).sum();

        model.addAttribute("studentName", "AKASH GANDHAM");
        model.addAttribute("studentId", "1365751");
        model.addAttribute("studentClass", "Grade 10");
        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "invoice";
    }
}
