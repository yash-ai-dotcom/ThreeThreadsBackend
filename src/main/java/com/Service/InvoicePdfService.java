package com.Service;

import com.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
public class InvoicePdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generateOrderInvoicePdf(Order order) throws Exception {
        Context context = new Context();
        context.setVariable("orderId", order.getId());
        context.setVariable("customerName", order.getCustomerName());
        context.setVariable("customerPhone", order.getCustomerPhone());
        context.setVariable("shippingAddress", order.getShippingAddress());
        context.setVariable("orderItems", order.getOrderItems());
        context.setVariable("grandTotal", order.getGrandTotal());
        context.setVariable("orderDate", order.getCreatedAt());

        String renderedHtml = templateEngine.process("invoice-template", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(renderedHtml);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }
}