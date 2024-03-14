document.addEventListener("DOMContentLoaded", function () {
  const params = new URLSearchParams(window.location.search);
  const deckId = params.get("deckId");

  if (deckId) {
    fetchCards(deckId);
  } else {
    console.error("Deck ID not provided");
  }
  document
    .getElementById("createCardBtn")
    .addEventListener("click", function () {
      const params = new URLSearchParams(window.location.search);
      const deckId = params.get("deckId");

      if (deckId) {
        window.location.href = `create-card.html?deckId=${deckId}`;
      } else {
        console.error("Deck ID is not provided");
      }
    });
});

function fetchCards(deckId) {
  fetch(`http://localhost:8080/decks/${deckId}/cards`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((cards) => {
      const tableBody = document
        .getElementById("cardsTable")
        .getElementsByTagName("tbody")[0];
      tableBody.innerHTML = "";

      cards.forEach((card) => {
        let row = tableBody.insertRow();
        let cellFront = row.insertCell(0);
        cellFront.textContent = card.front;
        let cellBack = row.insertCell(1);
        cellBack.textContent = card.back;
      });
    })
    .catch((error) => {
      console.error("Error fetching cards:", error);
    });
}
