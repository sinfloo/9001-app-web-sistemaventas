<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    <div class="col-sm-8">
        <div class="card">
            <div class="card-header">
                NUEVO MENSAJE
            </div>
            <form action="Controlador?menu=EnviarCorreo" method="post">
                <div class="card-body">
                    <div class="form-row align-items-center">
                        <div class="col-sm-12 my-1">
                            <label class="sr-only" for="inlineFormInputGroupUsername">Username</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><img src="img/icons/msj.png" alt=""/></div>                                
                                </div>
                                <input type="text" name="txtAsunto" class="form-control form-control-lg" id="inlineFormInputGroupUsername" placeholder="Asunto.." required="">
                            </div>
                        </div>
                        <div class="col-sm-12 my-1">
                            <label class="sr-only" for="inlineFormInputGroupUsername">Username</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><img src="img/icons/read_message.png" alt=""/></div>
                                </div>
                                <textarea type="text" name="txtMensaje" class="form-control"  placeholder="Mensaje..." required=""></textarea>
                            </div>
                        </div>
                    </div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-primary mt-4" data-toggle="modal" data-target="#staticBackdrop">
                        <img src="img/icons/add.png" alt=""/> Agrear Correo
                    </button>
                    <div class="form-group mt-2">
                        <label for="exampleFormControlSelect2">PARA:</label>
                        <select multiple class="form-control" id="exampleFormControlSelect2">
                            <c:forEach var="c" items="${clientesSeleted}" varStatus="s">
                                <option>${s.index+1}-${c.dir}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" name="accion" value="New" class="btn btn-danger">Cancelar</button>
                    <button type="submit" name="accion" value="enviar" class="btn btn-primary">Enviar Correo</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="Controlador?menu=EnviarCorreo" method="Post">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Seleccione Cliente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="table table-sm">
                        <thead class="thead-dark">
                            <tr class="text-center">
                                <th>#</th>                                
                                <th>NOMBRES</th>
                                <th>EMAIL</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="em" items="${clientes}" varStatus="n">
                                <tr>
                                    <td class="text-center">${n.index+1}</td>                                    
                                    <td>${em.getNom()}</td>
                                    <td>${em.getDir()}</td>                
                                    <td class="text-center">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input"  id="${em.id}" name="txtSend${em.id}">
                                            <label class="custom-control-label" for="${em.id}"></label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" name="accion" value="add"  class="btn btn-primary">Agregar</button>
                </div>
            </form>

        </div>
    </div>
</div>
<%@ include file="commons/footer.jspf"%>