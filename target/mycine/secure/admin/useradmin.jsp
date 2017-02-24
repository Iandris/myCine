<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - User Admin</title>
    <c:import url="../../jspinclude/navigationBar.jsp" />
    <c:import url="../../jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1>MyCine - User Admin</h1>
</div>

<div id="accordion">
    <c:forEach var="user" items="${users}" >
        <c:set var="addr" value="${user.address}" />
        <c:set var="st" value="${addresses[addr - 1].state}" />
        <h3 style="text-decoration:underline;"><strong>${user.lname}, ${user.fname}</strong></h3>
        <form action="/mycine/secure/admin/updateuser">
            <table width="100%">
                <tr>
                    <td colspan="3" width="25%">
                            <label for="uuid" class="control-label">UserID:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="uuid" name="uuid" width="100%" class="form-control" type="text" readonly="true" value="${user.uuid}" />
                    </td>
                    <td colspan="3" width="25%">
                            <label for="username" class="control-label">Username/Email:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="username" width="100%" class="form-control" name="user_name" type="text" value="${user.user_name}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3" width="25%">

                            <label for="cellphone" class="control-label">Cell Phone:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="cellphone" width="100%" class="form-control" name="cellnumber" type="text" value="${user.cellnumber}" />

                    </td>
                    <td colspan="3" width="25%">

                            <label for="password" class="control-label">Password:</label>
                    </td>
                    <td colspan="3" width="25%">
                       <input id="password"  width="100%" class="form-control" name="password" type="password" value="${user.password}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3" width="25%">

                            <label for="firstname" class="control-label">First Name:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="firstname" width="100%"  class="form-control" name="firstname" type="text" value="${user.fname}" />

                    </td>
                    <td colspan="3" width="25%">

                            <label for="lastname" class="control-label">Last Name:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="lastname" width="100%" class="form-control" name="lastname" type="text" value="${user.lname}" />

                    </td>
                </tr>
                <tr>
                    <td colspan="3" width="25%">

                        <label for="reminder" class="control-label">Reminder:</label>
                    </td>
                    <td colspan="3" width="25%">
                        <input id="reminder" width="100%" class="form-control" type="text" name="reminder" value="${user.reminderthreshold}" />

                    </td>
                    <td colspan="3" width="25%">

                        <label for="rental" class="control-label">Default Rental:</label>
                    </td>
                    <td colspan="3" width="25%">
                        <input id="rental" width="100%"  class="form-control" type="text" name="rental" value="${user.defaultrentalperiod}" />

                    </td>
                </tr>
            </table>

                <hr />
                <h4 style="text-decoration:underline;"><strong>Address</strong></h4>
            <table>
                <tr>
                    <td colspan="3" width="25%">

                            <input type="text" hidden="true" name="addrid" value="${user.address}" />
                            <label for="address" class="control-label">Address Line 1:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="address" width="100%"  class="form-control" type="text" name="address1" value="${addresses[addr -1].streetaddress1}" />

                    </td>
                    <td colspan="3" width="25%">

                            <label for="address2" class="control-label">Address Line 2:</label>
                    </td>
                    <td colspan="3" width="25%">
                            <input id="address2" width="100%"  class="form-control" name="address2" type="text" value="${addresses[addr -1].streetaddress2}" />

                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                            <label for="city" class="control-label">City:</label>
                    </td>
                    <td colspan="2">
                            <input id="city" width="100%" class="form-control" name="city" type="text" value="${addresses[addr -1].city}" />

                    </td>
                    <td colspan="2">

                            <label for="state" class="control-label">State:</label>
                    </td>
                    <td colspan="2">
                            <select class="form-control" id="state" name="state">
                                <c:set var="selectedState" value="${addresses[addr - 1].state}" />
                                <option value="Select...">Select...</option> 
                                <c:forEach var="state" items="${states}"> 
                                    <option value="${state.idstate}" ${state.idstate ==  selectedState? 'selected="selected"' : ''}>${state.longname}</option> 
                                </c:forEach>
                            </select>

                    </td>
                    <td colspan="2">

                                <%--<input id="state" name="state" type="text" value="${states[st -1].longname}" />--%>
                            <label for="zipcode" class="control-label">Zip:</label>
                    </td>
                    <td colspan="2">
                            <input id="zipcode" width="100%"  class="form-control" name="zip" type="text" value="${addresses[addr -1].zipcode}" />

                    </td>
                </tr>
                <tr>
                    <td colspan="4" width ="33%"></td>
                    <td colspan="4" width ="33%" style="text:center">
                        <input type="submit" class="form-control" value="Update User" />
                    </td>
                    <td colspan="4" width ="33%"></td>
                </tr>
            </table>
        </form>
    </c:forEach>
</div>


<%--<script type="text/javascript">--%>
    <%--$(document).ready( --%>
        <%--function() {--%>
            <%--$("#state").val(${states[st -1 ].idstate});--%>
        <%--});--%>
<%--</script>--%>
</body>
</html>
