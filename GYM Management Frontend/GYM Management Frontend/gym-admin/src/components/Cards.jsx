import "../styles/Cards.css";

const cardData = [
  {
    title: "Member Management",
    description:
      "Register new members, track membership status, and receive alerts for expiring plans."
  },
  {
    title: "Trainer Assignment",
    description:
      "Assign trainers to members and classes, and monitor their schedules efficiently."
  },
  {
    title: "Inventory Tracking",
    description:
      "Track gym equipment, update asset status, and log maintenance activities."
  },
  {
    title: "Attendance Monitoring",
    description:
      "Monitor member check-ins and check-outs in real time with full attendance history."
  },
  {
    title: "Class Scheduling",
    description:
      "Schedule fitness sessions, manage seat availability, and assign trainers."
  }
];

const Cards = () => {
  return (
    <section className="cards-section">
      <div className="cards-container">
        {cardData.map((card, index) => (
          <div className="card" key={index}>
            <h3>{card.title}</h3>
            <p>{card.description}</p>
          </div>
        ))}
      </div>
    </section>
  );
};


export default Cards;
