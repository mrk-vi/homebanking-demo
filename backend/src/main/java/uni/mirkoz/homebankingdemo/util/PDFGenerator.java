package uni.mirkoz.homebankingdemo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {

    public static byte[] bankingOperationPDFReport(List<BankingOperation> bankingOperations) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable table = new PdfPTable(8);

            Stream.of("# Operation", "Owner Iban", "State", "Type", "Amount", "Created At", "Recipient Iban", "Employee username")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA, 6);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (BankingOperation bankingOperation : bankingOperations) {
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA, 6);
                PdfPCell idCell = new PdfPCell(new Phrase(bankingOperation.getId().toString(), headFont));
                table.addCell(idCell);

                PdfPCell owner = new PdfPCell(new Phrase(bankingOperation.getBankAccount().getIban(), headFont));
                table.addCell(owner);

                PdfPCell state = new PdfPCell(new Phrase(String.valueOf(bankingOperation.getOperationState()), headFont));
                table.addCell(state);

                PdfPCell type = new PdfPCell(new Phrase(String.valueOf(bankingOperation.getOperationType()), headFont));
                table.addCell(type);

                PdfPCell amount = new PdfPCell(new Phrase(bankingOperation.getAmount().toString(), headFont));
                table.addCell(amount);

                PdfPCell createdAt = new PdfPCell(new Phrase(bankingOperation.getCreated().toString(), headFont));
                table.addCell(createdAt);

                PdfPCell recipient;
                try{
                    recipient = new PdfPCell(new Phrase(bankingOperation.getRecipientIban(), headFont));
                } catch (NullPointerException npe) {
                    recipient = new PdfPCell(new Phrase("n/a", headFont));
                }
                table.addCell(recipient);

                PdfPCell employee;
                try{
                    employee = new PdfPCell(new Phrase(bankingOperation.getEmployee().getUser().getUsername(), headFont));
                } catch (NullPointerException npe) {
                    employee = new PdfPCell(new Phrase("n/a", headFont));
                }
                table.addCell(employee);
            }
            document.add(table);

            document.close();
        }catch(DocumentException e) {
        }

        return out.toByteArray();
    }
}
