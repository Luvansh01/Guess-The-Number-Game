package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class GuessNumberServlet
 */
public class GuessNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessNumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve the user guess
	    int userGuess = Integer.parseInt(request.getParameter("num"));

	    // Get or create the session
	    HttpSession session = request.getSession();
	    Integer randomNumber = (Integer) session.getAttribute("randomNumber");
	    Integer guessCount = (Integer) session.getAttribute("guessCount");

	    // Initialize session attributes if not set
	    if (randomNumber == null) {
	        randomNumber = (int) (Math.random() * 100) + 1; // Generate random number between 1 and 100
	        session.setAttribute("randomNumber", randomNumber);
	        guessCount = 0;
	    }

	    guessCount++;
	    session.setAttribute("guessCount", guessCount);

	    // Prepare response message
	    String resultMessage;
	    if (userGuess == randomNumber) {
	        resultMessage = "You guessed it correctly! The random number was " + randomNumber + ".";
	        // Reset the game
	        session.removeAttribute("randomNumber");
	        session.removeAttribute("guessCount");
	    } else if (userGuess < randomNumber) {
	        resultMessage = "Too low! Try again.";
	    } else {
	        resultMessage = "Too high! Try again.";
	    }

	    // Send response back to client
	    response.setContentType("text/html");
	    response.getWriter().println("<html><body>");
	    response.getWriter().println("<p id='result'>" + resultMessage + "</p>");
	    response.getWriter().println("<p id='guessCount'>Number of guesses: " + guessCount + "</p>");
	    response.getWriter().println("</body></html>");
	}


	}

