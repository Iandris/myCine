<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Home</title>
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
        <h3>${user.lname}, ${user.fname}</h3>
        <form action="/mycine/secure/admin/updateuser">
            <table>
                <tr>
                    <td>
                        <div class="input-group">
                            <label for="uuid" class="control-label">UserID:</label>
                            <input id="uuid" name="uuid" class="form-control" type="text" readonly="true" value="${user.uuid}" />
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <label for="username" class="control-label">Username/Email:</label>
                            <input id="username" class="form-control" name="user_name" type="text" value="${user.user_name}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group">
                            <label for="cellphone" class="control-label">Cell Phone:</label>
                            <input id="cellphone" class="form-control" name="cellnumber" type="text" value="${user.cellnumber}" />
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <label for="password" class="control-label">Password:</label>
                            <input id="password" class="form-control" name="password" type="password" value="${user.password}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group">
                            <label for="firstname" class="control-label">First Name:</label>
                            <input id="firstname" class="form-control" name="firstname" type="text" value="${user.fname}" />
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <label for="lastname" class="control-label">Last Name:</label>
                            <input id="lastname" class="form-control" name="lastname" type="text" value="${user.lname}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group">
                            <input type="text" hidden="true" name="addrid" value="${user.address}" />
                            <label for="address" class="control-label">Address Line 1:</label>
                            <input id="address" class="form-control" type="text" name="address1" value="${addresses[addr -1].streetaddress1}" />
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <label for="address2" class="control-label">Address Line 2:</label>
                            <input id="address2" class="form-control" name="address2" type="text" value="${addresses[addr -1].streetaddress2}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="input-group">
                            <label for="city" class="control-label">City:</label>
                            <input id="city" class="form-control" name="city" type="text" value="${addresses[addr -1].city}" />
                            <label for="state" class="control-label">State:</label>
                            <select class="form-control" id="state" name="state">
                                <c:set var="selectedState" value="${addresses[addr - 1].state}" />
                                <option value="Select...">Select...</option> 
                                <c:forEach var="state" items="${states}"> 
                                    <option value="${state.idstate}" ${state.idstate ==  selectedState? 'selected="selected"' : ''}>${state.shortname}</option> 
                                </c:forEach>

                            </select>

                                <%--<input id="state" name="state" type="text" value="${states[st -1].longname}" />--%>
                            <label for="zipcode" class="control-label">Zip Code:</label>
                            <input id="zipcode" class="form-control" name="zip" type="text" value="${addresses[addr -1].zipcode}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group">
                            <label for="reminder" class="control-label">Reminder Threshold:</label>
                            <input id="reminder" class="form-control" type="text" name="reminder" value="${user.reminderthreshold}" />
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <label for="rental" class="control-label">Default Rental Period:</label>
                            <input id="rental" class="form-control" type="text" name="rental" value="${user.defaultrentalperiod}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" class="form-control" value="Update User" />
                    </td>
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
