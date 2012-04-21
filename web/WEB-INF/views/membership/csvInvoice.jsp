<%@page contentType="text/csv" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><% response.addHeader("Content-Disposition", "attachment;filename=invoice.csv"); %>Description,Unit Cost,Quantity,Total Cost
<c:forEach items="${invoice.invoiceItems}" var="item">${item.description},${item.unitCost},${item.quantity},${item.totalCost}
</c:forEach>