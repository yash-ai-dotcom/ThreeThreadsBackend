package com.Service;

import com.Entity.Order;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
public class InvoicePdfService {

    private final TemplateEngine templateEngine;

    public InvoicePdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generateOrderInvoicePdf(Order order) throws Exception {
        Context context = new Context();

        // Pass order details to Thymeleaf template using matching entity getters
        context.setVariable("order", order);
        context.setVariable("items", order.getItems()); // Replaces getOrderItems()
        context.setVariable("totalAmount", order.getTotalAmount()); // Replaces getGrandTotal()

        String htmlContent = templateEngine.process("invoice-template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }
}