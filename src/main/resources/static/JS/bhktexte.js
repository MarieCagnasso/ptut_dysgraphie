

let d = false;
let td = 0;
let tf = 0;
let te = 0;
let num_point=1;
let liste_point = [];

let boutton_De_Validation = document.getElementById("btn-valid");

function deroule(element) {
    if (d === false) {
        for (let i = 0; i < document.getElementsByTagName('LI').length; i++) {
            document.getElementsByTagName('LI')[i].style.display = "none";
            document.getElementById("details").getElementsByTagName("img")[i].style.transform = "rotate(180deg)";
        }
        element.style.display = "block";
        element.style.height = "100%";
        element.children[1].style.display = "block";
        d = true;
    } else {
        for (let i = 0; i < document.getElementsByTagName('LI').length; i++) {
            document.getElementsByTagName('LI')[i].style.display = "block";
            element.style.height = "24.1%";
            document.getElementById("details").getElementsByTagName("img")[i].style.transform = "rotate(0deg)";
        }
        element.children[1].style.display = "none";
        d = false;
    }
}

// document.getElementById("results").style.display="none";
// document.getElementById("restart").style.display="none";
color = "#000";
canvas_draw.width = canvas_draw.parentNode.offsetWidth - 10;
canvas_draw.height = canvas_draw.parentNode.offsetHeight - 10;


//change pen color
function changeColor() {
    color = '#' + (Math.random() * 0xFFFFFF << 0).toString(16);
}

//récupérer pression
// Pressure.set('#canvas_draw', {
//     change: function (force, evt0) {
//         chronoOn();
//         $.ajax({
//             type: 'post',
//             url: '/addPression',
//             data: {
//                 "pression": force
//             },
//             error: function (resultat, statut, error) {
//                 console.log("error pression")
//             }
//         });
//     }
// }, { polyfill: true });

// start drawing
function moveDrawligne(oEvent) {
    var oCanvas = oEvent.currentTarget,
        oCtx = null, oPos = null;
    if (oCanvas.bDraw == false) {
        return false;
    }//if
    oPos = getPosition(oEvent, oCanvas);
    oCtx = oCanvas.getContext('2d');

    //dessine
    oCtx.strokeStyle = color;
    oCtx.lineWidth = 1;
    oCtx.beginPath();
    oCtx.moveTo((oCanvas.posX), oCanvas.posY);
    oCtx.lineTo(oPos.posX, oPos.posY);
    oCtx.stroke();

    oCanvas.posX = oPos.posX;
    oCanvas.posY = oPos.posY;
}

function getPosition(oEvent, oCanvas) {
    var oRect = oCanvas.getBoundingClientRect(),
        oEventEle = oEvent.changedTouches ? oEvent.changedTouches[0] : oEvent;
    var x = (oEventEle.clientX - oRect.left) / (oRect.right - oRect.left) * oCanvas.width;
    var y = (oEventEle.clientY - oRect.top) / (oRect.bottom - oRect.top) * oCanvas.height;
    // $.ajax({
    //     type:'post',
    //     url:'/addPoint',
    //     data: {
    //         "pointX":parseInt(x),
    //         "pointY":parseInt(y)
    //     },
    //     error:function(resultat,statut,error){
    //         console.log(error.responseText)
    //     }
    // });
    let inter=0;
    if(liste_point.length !=0){
        inter=oEvent.timeStamp-liste_point[liste_point.length-1].tps
    }

    liste_point.push({
        x: parseInt(x),
        y: parseInt(y),
        num:num_point,
        inter:inter,
        tps: oEvent.timeStamp,
    }
    );
    
    num_point++;

    return {
        posX: x,
        posY: y,
    };

}

function downDrawligne(oEvent) {
    t1 = Date.now();
    changeColor();
    oEvent.preventDefault();
    var oCanvas = oEvent.currentTarget,
        oPos = getPosition(oEvent, oCanvas);
    oCanvas.posX = oPos.posX;
    oCanvas.posY = oPos.posY;
    oCanvas.bDraw = true;
    capturer(false);
}

function upDrawligne(oEvent) {
    t2 = Date.now()
    te = te + (t2 - t1)
    var oCanvas = oEvent.currentTarget;
    oCanvas.bDraw = false;
    //capturer(true);
}

function initCanvas() {
    var oCanvas = document.getElementById("canvas_draw");
    oCanvas.bDraw = false;
    oCtx = oCanvas.getContext('2d');
    oCanvas.addEventListener("mousedown", downDrawligne);
    oCanvas.addEventListener("mouseup", upDrawligne);
    oCanvas.addEventListener("mousemove", moveDrawligne);
    oCanvas.addEventListener("touchstart", downDrawligne);
    oCanvas.addEventListener("touchend", upDrawligne);
    oCanvas.addEventListener("touchmove", moveDrawligne);
}
/**
 * Récupère le canva sous forme d'image
 */
function capturer(bAction) {
    var oCapture = document.getElementById("capture_canvas");
    oCapture.innerHTML = '';
    if (bAction == true) {
        var oImage = document.createElement('img'),
            oCanvas = document.getElementById("canvas_draw");
        oImage.src = oCanvas.toDataURL("image/png");
        oCapture.appendChild(oImage);
    }
}

/**
 * Vide les dessin du canvas
 */
function nettoyer(oEvent) {
    var oCanvas = document.getElementById("canvas_draw"),
        oCtx = oCanvas.getContext('2d');
    oCtx.clearRect(0, 0, oCanvas.width, oCanvas.height);
    capturer(false);
    liste_point=[];

    // $.ajax({
    //     url: '/erase',
    //     success: function (response) {
    //         console.log("Canvas effacé");
    //     }
    // });
}

document.addEventListener('DOMContentLoaded', function () {
    initCanvas();
    document.getElementById("button_clear").addEventListener("click", nettoyer);
});


// remove callback function when mouse up
canvas_draw.onmouseup = function (evt) {
    canvas_draw.onmousemove = {};
};

var chrono = false;
function chronoOn() {
    if (chrono == false) {
        chrono = true;
        $.ajax({
            url: '/init',
            success: function (response) {
                console.log("Lancement chrono");
            }
        });
    }
}
var minutes = document.getElementById("minutes");
var seconds = document.getElementById("seconds");
var myVar = setInterval(countUp, 1000);
function countUp() {
    console.log(chrono)
    if (chrono == true) {
        if (parseInt(seconds.innerText) == 59) {
            minutes.innerText = parseInt(minutes.innerText) + 1;
            if (minutes.innerText.length < 2) minutes.innerText = "0" + minutes.innerText;
            seconds.innerText = "00";
        } else {
            seconds.innerText = parseInt(seconds.innerText) + 1;
            if (seconds.innerText.length < 2) seconds.innerText = "0" + seconds.innerText;
        }
    }
};

function stopCount() {
    chrono = false;
    //document.getElementsByClassName("voile")[0].style.display = "block";
    //setTimeout(results, 2000);*
    addResultat();
    //document.location.href="http://localhost:8081/tableau"
}
boutton_De_Validation.addEventListener("click", stopCount)
// function results() {
//     $.ajax({
//         url: '/results',
//         method: 'get',
//         data: {
//             "tempsE": te
//         },
//         datatype: 'html',
//         success: function (response) {
//             console.log("Envoi des résultats");
//             $("#details").html(response);
//             $("ul").unwrap();
//             document.getElementById("canvas_draw").style.zIndex = "0";
//             document.getElementById("capture_canvas").style.zIndex = "4";
//             document.getElementById("button_clear").style.display = "none";
//             document.getElementById("pingouin").style.display = "none";
//             document.getElementById("time").style.display = "none";
//             document.getElementsByClassName("voile")[0].style.display = "none";
//             document.getElementById("phrase").style.display = "none";
//             document.getElementById("results").style.display = "block";
//             document.getElementById("restart").style.display = "block";
//             document.getElementById("btn-valid").style.display = "none";
//             document.getElementById("details").style.display = "block";
//         },
//         error: function (e) {
//             console.log(e);
//         }
//     });

// }

// function download() {
//     console.log("Lancement du téléchargement ...");
//     $.ajax({
//         url: '/download',
//         success: function (response) {
//             alert("Fichier Excel téléchargé !")
//         }
//     });
// }


function addResultat(){
    let data={};
    prenomPatient="titouan"
    nomPatient="Duboit"
    sexe="Garçon"
    niveau="CP"
    data={
        prenomPatient,
        nomPatient,
        sexe,
        niveau,
        liste_point
    };
    $.ajax({ 
        method:'POST',
        url: 'http://localhost:8081/tableau/add',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(){
            console.log('bien joué beau gosse');
        },
        error: function(error){
            console.log(error);
        },
    })
}