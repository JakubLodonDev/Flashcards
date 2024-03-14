document.addEventListener("DOMContentLoaded", function () {
  document
    .getElementById("newCardForm")
    .addEventListener("submit", function (e) {
      e.preventDefault();

      const params = new URLSearchParams(window.location.search);
      const deckId = params.get("deckId");
      const frontName = document.getElementById("front").value;
      const backName = document.getElementById("back").value;

      fetch(`http://localhost:8080/decks/${deckId}/cards`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          front: frontName,
          back: backName,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Deck created successfully", data);
          window.location.href = `view-cards.html?deckId=${deckId}`;
        })
        .catch((error) => {
          console.error("Error creating deck:", error);
        });
    });
});
