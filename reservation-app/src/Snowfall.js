// Snowfall.js
import { useEffect } from "react";
import "./Snowfall.css";

const Snowfall = () => {
  useEffect(() => {
    const createSnowflake = () => {
      const snowflake = document.createElement("div");
      snowflake.className = "snowflake";

      // Set the initial position to a random X coordinate at the top of the screen
      snowflake.style.left = `${Math.random() * window.innerWidth}px`;
      snowflake.style.top = `${Math.random() * window.innerHeight}px`; // Set a negative value to start above the screen

      document.body.appendChild(snowflake);

      // Remove the snowflake after it falls out of view
      snowflake.addEventListener("animationend", () => {
        snowflake.remove();
      });
    };

    // Create snowflakes periodically
    const snowfallInterval = setInterval(createSnowflake, 500);

    // Clean up on component unmount
    return () => {
      clearInterval(snowfallInterval);
    };
  }, []);

  return null; // This component doesn't render anything
};

export default Snowfall;
