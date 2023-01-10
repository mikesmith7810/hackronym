package com.xdesign.hackronym.contents.retriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CodeRetriever {
	public String retrieveCodeFor( final String uri ) throws IOException {
		final Writer writer = new StringWriter();
		final char[] buffer = new char[2048];

		final URL url = new URL( uri );
		final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		try ( final InputStream inputStream = httpURLConnection.getInputStream() ) {

			final Reader reader = new BufferedReader(
					new InputStreamReader( inputStream, "UTF-8" ) );
			int counter;
			while ( ( counter = reader.read( buffer ) ) != -1 ) {
				writer.write( buffer, 0, counter );
			}

		} catch ( Exception e ) {
			log.info( "Error retrieving github source code : " + e.getMessage() );
		}
		return writer.toString();

	}
}
