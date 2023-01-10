package com.xdesign.hackronym.controller;

import javax.servlet.annotation.WebServlet;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

@WebServlet("/slack/events")
public class SlackAppController extends SlackAppServlet {

	public SlackAppController( final App app ) {
		super( app );
	}
}
