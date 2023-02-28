import { object } from "prop-types";
import { useEffect, useState } from "react";

function Template({ data, postSubmit }) {
  const [values, setValues] = useState({});

  const handleFormSubmission = (e) => {
    e.preventDefault();
    let valid = true;
    Object.entries(data.fields).map(([field, detail]) => {
      if (detail.required && values[field] == "") {
        alert(field + " can't be blank");
        valid = false;
        return;
      }
      if (detail.regex) {
        const re = new RegExp(detail.regex, "g");
        if (!re.test(values[field])) {
          alert(detail.regexErrorMessage);
          valid = false;
          return;
        }
      }
    });
    if (!valid) {
      return;
    }
    fetch("http://localhost:8080/rudderstacks/v1/sourceConfigs/" + data.type, {
      method: "POST",
      body: JSON.stringify({
        fields: values,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((res) => {
        if (res.status >= 200) {
          postSubmit("Data has been saved successfully");
        } else {
          postSubmit("Failed to save data, statusCode:" + res.status);
        }
      })
      .catch((err) => {
        postSubmit("Unknown err");
      });
  };

  const changeValue = (e, field) => {
    values[field] = e.target.value;
    setValues(values);
  };

  useEffect(() => {
    Object.entries(data.fields).map(([field, detail]) => {
      if (detail.type === "input") {
        values[field] = "";
      } else if (detail.type === "checkbox") {
        values[field] = "off";
      } else if (detail.type === "singleSelect") {
        values[field] = detail.options[0].value;
      }
    });
    setValues(values);
  }, []);
  return (
    <form className="the-form">
      {Object.entries(data.fields).map(([field, detail]) => {
        console.log(field);
        console.log(detail);
        return (
          <div className="form-field" key={field}>
            {detail.label != undefined && <label>{detail.label}</label>}
            {detail.type === "input" && (
              <input
                type="text"
                placeholder={detail.placeholder}
                name={field}
                value={values[field]}
                onChange={(e) => changeValue(e, field)}
              />
            )}
            {detail.type === "checkbox" && (
              <input
                type="checkbox"
                name={field}
                on={values[field]}
                onChange={(e) => changeValue(e, field)}
              />
            )}
            {detail.type === "singleSelect" && (
              <select name={field} onChange={(e) => changeValue(e, field)}>
                {detail.options.map((op) => {
                  return (
                    <option value={op.value} key={op.value}>
                      {op.label}
                    </option>
                  );
                })}
              </select>
            )}
          </div>
        );
      })}
      <button onClick={handleFormSubmission}>submit</button>
    </form>
  );
}

export default Template;
