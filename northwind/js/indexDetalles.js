const URL = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/DetallesPedidos/pedido";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  const queryStr = window.location.search.substring(1);
  const parametro = queryStr.split("=");
  iddetalles = parametro[1];  
  console.log("Estoy en orden ", parametro[1], " ", iddetalles)
  const peticionHTTP = fetch(URL + "/" + iddetalles);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((detalles) => {
      let tblBody = document.getElementById("id_tblDetalles");
      for (const details of detalles) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        //elemento = document.getElementById("producto");
        elemento.innerHTML = details.order_id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.product_id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.quantity;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.unit_price + ` €`;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.discount + ` %`;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML =
         `<button class="btn btn-link" onclick="editaDetalles(${details.id})"><i class="bi-pencil"></i></button>` +
         `<button style="color:red;" class="btn btn-link"  onclick="borrarDetalle(${details.id})"><i class="bi-x-circle"></i></button>`+ 
         `<button style="color:#20c997"; class="btn btn-link" onclick="indexProducto(${details.product_id})"><i class="bi bi-basket2"></i></button>`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }

  
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de los detalles!<br>" + error, false, "error");
    });

}

function editaDetalles(iddetalles) {
  window.location.href = `editarDetalles.html?iddetalles=${iddetalles}`;
}

function indexProducto(iddetalles) {
  window.location.href = `indexProducto.html?iddetalles=${iddetalles}`;
}

function borrarDetalle(iddetalles) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el detalles ${iddetalles}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarDetalleAPI(iddetalles);
  });
}

function borrarDetalleAPI(iddetalles) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(URL + "/" + iddetalles, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else 
      {
        throw new Error(`Fallo al borrar, el servidor responde con ${respuesta.status}-${respuesta.statusText}`);
      }
        
    })
    .then((respuesta) => {
      modalWait.hide();
      muestraMsg(`¡detalles ${iddetalles} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "detalles NO borrado",
        "¿Es posible que este Detalle lo tenga algun cliente? 🤔<br>" + error,
        false,
        "error"
      );
    });
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

