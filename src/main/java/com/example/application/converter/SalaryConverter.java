package com.example.application.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class SalaryConverter implements Converter<String, Double> {

	private static final String SALARY_REGEX = "^([0-9]*)(\\.|\\,)([0-9]{1,2})$";

	@Override
	public Result<Double> convertToModel(String value, ValueContext context) {
		Result<Double> result = null;
		if (value.matches(SALARY_REGEX)) {
			final Matcher finder = Pattern.compile(SALARY_REGEX).matcher(value);
			while (finder.find()) {
				try {
					result = Result.ok(Double.parseDouble(finder.group().replace(',', '.')));
				} catch (NumberFormatException ex) {
					result = Result.error(ex.getMessage());
				}
			}
		} else {
			result = Result.error("Invalid Format Salary");
		}
		return result;
	}

	@Override
	public String convertToPresentation(Double value, ValueContext context) {
		return String.format(context.getLocale().get(), "%1$.2f", value);

	}

}
