import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import Cards from "../components/Cards";
import Footer from "../components/Footer";
import "../styles/LandingPage.css";

const LandingPage = () => {
  return (
    <div className="page-wrapper">
      <Navbar />
      <Hero />
      

  <Cards />

      <Footer />
    </div>
  );
};

export default LandingPage;
