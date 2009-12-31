package bakersoftware.maven_replacer_plugin;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class TokenReplacerTest {
	private TokenReplacer replacer;

	@Before
	public void setUp() {
		replacer = new TokenReplacer();
	}

	@Test
	public void shouldReplaceNonRegexTokenWithValue() throws Exception {
		String results = replacer.replaceNonRegex("some $token$", "$token$", "value");
		assertEquals("some value", results);
	}

	@Test
	public void shouldReplaceRegexTokenWithValue() throws Exception {
		String results = replacer.replaceRegex("some token", "t.k.n", "value", Pattern.MULTILINE);
		assertEquals("some value", results);
	}

	@Test
	public void shouldReplaceTokenWithEmptyValue() throws Exception {
		String results = replacer.replaceRegex("some token", "t.k.n", null, Pattern.MULTILINE);
		assertEquals("some ", results);
	}

	@Test
	public void shouldReplaceTokenInMulipleLines() throws Exception {
		String results = replacer.replaceRegex("some\ntoken", "t.k.n", null, Pattern.MULTILINE);
		assertEquals("some\n", results);
	}

	@Test
	public void shouldHandleEmptyContentsGracefully() {
		String results = replacer.replaceRegex("", "anything", "anything", Pattern.MULTILINE);
		assertEquals("", results);

		results = replacer.replaceNonRegex("", "anything", "anything");
		assertEquals("", results);
	}
}
