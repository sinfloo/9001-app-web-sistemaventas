<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="ml-4 mt-4 mr-4">
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Cliente" method="POST">
                        <div class="form-group">
                            <label>Dni</label>
                            <input type="text" value="${cliente.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${cliente.getNom()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" value="${cliente.getDir()}" name="txtTel" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <select class="form-control" name="txtEstado">
                                <option value="1">ACTIVO</option>
                                <option value="0">INACTIVO</option>
                            </select>
                        </div>                        
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>                         
            </div>
        </div>                     
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <table class="table" style="width: 100%" id="example">
                        <thead>
                            <tr class="text-center">
                                <th>#</th>                                
                                <th>NOMBRES</th>
                                <th>EMAIL</th>
                                <th>DNI</th>
                                <th>ESTADO</th>                                   
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="em" items="${clientes}" varStatus="n">
                                <tr>
                                    <td class="text-center">${n.index+1}</td>                                    
                                    <td>${em.getNom()}</td>
                                    <td>${em.getDir()}</td>
                                    <td>${em.getDni()}</td>
                                    <c:if test="${em.getEs()=='1'}">
                                        <td class="text-center">ACTIVO</td>
                                    </c:if>
                                    <c:if test="${em.getEs()=='0'}">
                                        <td class="text-center">INACTIVO</td>
                                    </c:if>                    
                                    <td class="text-center">
                                        <a class="btn btn-outline-warning btn-sm" href="Controlador?menu=Cliente&accion=Editar&id=${em.getId()}"><i class="bi bi-pencil-square"></i></a>
                                        <a class="btn btn-outline-danger btn-sm" href="Controlador?menu=Cliente&accion=Delete&id=${em.getId()}"><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>      
</div>      
<%@ include file="commons/footer.jspf"%>

