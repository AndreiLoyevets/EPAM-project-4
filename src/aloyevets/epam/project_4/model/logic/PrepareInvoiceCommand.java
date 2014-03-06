package aloyevets.epam.project_4.model.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrepareInvoiceCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String buttonPressed =  request.getParameterNames().nextElement();
		Pattern patternSuite = Pattern.compile("(suite|coupe|berth)(.)");
		Matcher matcher = null;
		
		// Determine the wagon type and route id
		matcher = patternSuite.matcher(buttonPressed);
		if (matcher.matches()) {
			String wagonType = matcher.group(1);
			String routeID = matcher.group(2);
		}
		
	}

}
