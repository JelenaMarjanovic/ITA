<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    <table border="1">
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Address</td>
                            <td>E-mail</td>
                            <td>Phone</td>
                            <td>Number of orders</td>
                            <td></td>
                        </tr>
                        <c:forEach items="${customers}" var="customer">
                            <tr>
                                <td>${customer.customerId}</td>
                                <td>${customer.name}</td>
                                <td>${customer.address}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.numberOfOrders}</td>
                                <td><a href="managecustomer?customerId=${customer.customerId}">Manage</a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="7">
                                <c:forEach begin="1" end="${totalpages}" varStatus="counter">
                                    <a href="customers?page=${counter.count}">${counter.count}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>