'use strict';

const output = document.getElementById('output');

document.getElementById('footballerForm').addEventListener("submit", function (event) {
    event.preventDefault();

    const form = event.target;

    const footballer = {
        name: form.footballerName.value,
        position: form.footballerPosition.value,
        cardType: form.footballerCardType.value,
        rating: form.footballerRating.value,
    }

    axios.post("/createFootballer", footballer)
        .then(res => {
            console.log("RESPONSE: ", res);
            form.footballerName.focus();
            form.reset();
            renderFootballers();
        })
        .catch(err => console.error(err));

    console.log("footballer: ", footballer);
});

function renderFootballers() {
    axios.get("/getFootballers")
        .then(res => {
            console.log("footballerS: ", res.data);
            output.innerHTML = "";
            for (let footballer of res.data) {
                const footballerCol = document.createElement("div");
                footballerCol.className = "col";

                const footballerCard = document.createElement("div");
                footballerCard.className = "card";
                footballerCol.appendChild(footballerCard);

                const footballerDiv = document.createElement("div");
                footballerDiv.className = "card-body";
                footballerCard.appendChild(footballerDiv);

                const footballerName = document.createElement("h2");
                footballerName.innerText = footballer.name;
                footballerDiv.appendChild(footballerName);

                const footballerRating = document.createElement("p");
                footballerRating.innerText = footballer.rating + " Rated.";
                footballerDiv.appendChild(footballerRating);

                const footballerPosition = document.createElement("p");
                footballerPosition.innerText = footballer.position;
                footballerDiv.appendChild(footballerPosition);

                const footballerCardType = document.createElement("p");
                footballerCardType.innerText = footballer.cardType;
                footballerDiv.appendChild(footballerCardType);

                const footballerDelete = document.createElement("button");
                footballerDelete.innerText = "DESTROY";
                footballerDelete.addEventListener("click", function () {
                    deleteFootballer(footballer.id);
                });

                footballerDiv.appendChild(footballerDelete);

                output.appendChild(footballerCol);
            }
        })
        .catch(err => console.error(err));
}

function deleteFootballer(id) {
    axios.delete("/removeFootballer/" + id)
        .then(res => {
            console.log(res);
            renderFootballers();
        })
        .catch(err => console.error(err));
}

renderFootballers();