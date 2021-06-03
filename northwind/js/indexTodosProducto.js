const URL = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/productos";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  const queryStr = window.location.search.substring(1);
  const parametro = queryStr.split("=");
  idproductos = parametro[1];  
  console.log("Estoy en orden ", parametro[1], " ", idproductos)
  const peticionHTTP = fetch(URL);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((productos) => {
      let tblBody = document.getElementById("id_tblProductos");
      for (const products of productos) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = products.id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.product_name;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.product_code;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.quantity_per_unit;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.standard_cost + ` €`;
        fila.appendChild(elemento);


        tblBody.appendChild(fila);
      }

  
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de los productos!<br>" + error, false, "error");
    });

}

function indexProducto() {
  window.location.href = "indexProducto.html";
}

/**
 * Muestra un mensaje en el modal
 */
function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "Close") {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo = "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " + titulo;
      }
      break;
    case "question":
      {
        titulo = "<i style='color:blue' class='bi bi-question-circle-fill'></i> " + titulo;
      }
      break;
    default:
      {
        titulo = "<i style='color:green' class='bi bi-check-circle-fill'></i> " + titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton ? "block" : "none";

  myModal.show();
}
