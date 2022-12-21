package spring.thymeleaf.invoiceTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.thymeleaf.invoiceTracker.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
