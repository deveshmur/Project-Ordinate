import { useEffect, useState } from "react";
import { fetchTemplates, processText } from "./api/ordinateApi";

export default function App() {
  const [templates, setTemplates] = useState([]);
  const [selectedTemplate, setSelectedTemplate] = useState("");
  const [rawText, setRawText] = useState("");
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchTemplates()
      .then(setTemplates)
      .catch((err) => setError(err.message));
  }, []);

  async function handleSubmit(e) {
    e.preventDefault();
    setError(null);
    setResult(null);
    setLoading(true);

    try {
      const data = await processText(selectedTemplate, rawText);
      setResult(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={{ padding: "2rem", maxWidth: "800px", margin: "0 auto" }}>
      <h1>Project Ordinate</h1>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "1rem" }}>
          <label>
            Template:
            <select
              value={selectedTemplate}
              onChange={(e) => setSelectedTemplate(e.target.value)}
              required
            >
              <option value="">-- select --</option>
              {templates.map((t) => (
                <option key={t.key} value={t.key}>
                  {t.key}
                </option>
              ))}
            </select>
          </label>
        </div>

        <div style={{ marginBottom: "1rem" }}>
          <label>
            Raw text:
            <br />
            <textarea
              rows={6}
              style={{ width: "100%" }}
              value={rawText}
              onChange={(e) => setRawText(e.target.value)}
              required
            />
          </label>
        </div>

        <button type="submit" disabled={loading}>
          {loading ? "Processing..." : "Process"}
        </button>
      </form>

      {result && (
        <>
          <h2>Result</h2>
          <pre
            style={{
              background: "#f4f4f4",
              color: "#111",
              padding: "1rem",
              overflowX: "auto",
            }}
          >
            {JSON.stringify(result, null, 2)}
          </pre>
        </>
      )}
    </div>
  );
}
