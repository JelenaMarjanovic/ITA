<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <form action="manageproduct" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="productId" value="${product.productId}" />
                        Title:<br />
                        <input type="text" name="title" value="${product.title}" /><br />
                        <br />Author:<br />
                        <input type="text" name="author" value="${product.author}" /><br />
                        <br />Published year:<br />
                        <input type="text" name="publishedYear" value="${product.publishedYear}" /><br />
                        <br />Pages:<br />
                        <input type="text" name="pagesNo" value="${product.pagesNo}" /><br />
                        <br />ISBN:<br />
                        <input type="text" name="isbn" value="${product.isbn}" /><br />
                        <br />Price:<br />
                        <input type="text" name="price" value="${product.price}" /><br />
                        <br />Stock:<br />
                        <input type="text" name="stock" value="${product.stock}" /><br />
                        <br />Category:<br />
                        <select name="categoryId">
                            <c:forEach items="${categories}" var="category">
                                <option <c:if test="${category.categoryId==product.categoryId.categoryId}">selected</c:if> value="${category.categoryId}">${category.name}</option>
                            </c:forEach>
                        </select><br /><br />
                        <br />Photo:<br />
                        <input type="file" name="photo" /><br />
                        <div class="buy_now_button">
                            <input id="button" type="submit" name="create" value="Create" onclick="form.action='createproduct';" />
                            <input id="button" type="submit" name="update" value="Update" onclick="form.action='updateproduct';" />
                            <input id="button" type="submit" name="delete" value="Delete" onclick="form.action='deleteproduct';" />
                        </div>
                    </form>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>