<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <form action="managecustomer" method="post">
                        <input type="hidden" name="customerId" value="${customer.customerId}" />
                        Name:<br />
                        <input type="text" name="name" value="${customer.name}" /><br />
                        <br />Address:<br />
                        <textarea name="address" rows="5" cols="35" >${customer.address}</textarea><br />
                        <br />Email:<br />
                        <input type="text" name="email" value="${customer.email}" /><br />
                        <br />Phone:<br />
                        <input type="text" name="phone" value="${customer.phone}" /><br />
                        <div class="buy_now_button">
                            <input id="button" type="submit" name="create" value="Create" onclick="form.action='createcustomer';" />
                            <input id="button" type="submit" name="update" value="Update" onclick="form.action='updatecustomer';" />
                            <input id="button" type="submit" name="delete" value="Delete" onclick="form.action='deletecustomer';" />
                        </div>
                    </form>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>