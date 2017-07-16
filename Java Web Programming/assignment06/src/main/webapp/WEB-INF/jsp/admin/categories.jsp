<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <form action="managecategory" method="post">
                        <select onchange="if(this.value!==-1) window.location='./categories?categoryId='+this.value" name="categoryId">
                            <option value="-1">Select category:</option>
                            <c:forEach items="${categories}" var="category">
                                <option <c:if test="${category.categoryId==selectedCategory.categoryId}">selected</c:if> value="${category.categoryId}">${category.name}</option>
                            </c:forEach>
                        </select><br />
                        <br />Name:<br />
                        <input type="text" name="name" value="${selectedCategory.name}" /><br /><br />
                        <div class="buy_now_button">
                            <input id="button" type="submit" name="create" value="Create" onclick="form.action='createcategory';" />
                            <input id="button" type="submit" name="update" value="Update" onclick="form.action='updatecategory';" />
                            <input id="button" type="submit" name="delete" value="Delete" onclick="form.action='deletecategory';" />
                        </div>
                    </form>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>