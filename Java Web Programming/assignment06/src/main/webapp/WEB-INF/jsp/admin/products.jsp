<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    <table border="1">
                        <tr>
                            <td>ID</td>
                            <td>Title</td>
                            <td>Author</td>
                            <td>Published</td>
                            <td>Pages</td>
                            <td>ISBN</td>
                            <td>Price</td>
                            <td>Photo</td>
                            <td>Stock</td>
                            <td>Category</td>
                            <td></td>
                        </tr>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>${product.productId}</td>
                                <td>${product.title}</td>
                                <td>${product.author}</td>
                                <td>${product.publishedYear}</td>
                                <td>${product.pagesNo}</td>
                                <td>${product.isbn}</td>
                                <td>${product.price}</td>
                                <td><img src="../resources/photos/${product.photo}" /></td>
                                <td>${product.stock}</td>
                                <td>${product.categoryId.name}</td>
                                <td><a href="manageproduct?productId=${product.productId}">Manage</a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="11">
                                <c:forEach begin="1" end="${totalpages}" varStatus="counter">
                                    <a href="products?page=${counter.count}">${counter.count}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>