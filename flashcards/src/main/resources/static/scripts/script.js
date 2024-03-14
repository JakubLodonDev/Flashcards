document.addEventListener("DOMContentLoaded", function () {
  displayDecks();
});

function displayDecks() {
  fetch("http://localhost:8080/decks")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((decks) => {
      const tableBody = document
        .getElementById("decksTable")
        .getElementsByTagName("tbody")[0];
      tableBody.innerHTML = "";

      decks.forEach((deck) => {
        let row = tableBody.insertRow();
        let cellName = row.insertCell(0);
        cellName.textContent = deck.name;

        let cellActions = row.insertCell(1);
        let viewButton = document.createElement("button");
        viewButton.textContent = "View Cards";
        viewButton.classList.add("btn");
        viewButton.dataset.deckId = deck.deckId;
        viewButton.addEventListener("click", function () {
          viewCards(deck.deckId);
        });
        cellActions.appendChild(viewButton);
      });
    })
    .catch((error) => {
      console.error("Error fetching decks:", error);
    });
}

function createDeck() {
  window.location.href = "create-deck.html";
}

function viewCards(deckId) {
  window.location.href = "view-cards.html?deckId=" + deckId;
}
