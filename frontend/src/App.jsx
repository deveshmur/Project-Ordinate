import { useEffect, useMemo, useState } from "react";
import { fetchTemplates, processText } from "./api/ordinateApi";
import Voice from "./Voice";

function Badge({ children }) {
  return (
    <span
      style={{
        display: "inline-block",
        padding: "0.15rem 0.5rem",
        borderRadius: "999px",
        fontSize: "0.8rem",
        background: "rgba(255,255,255,0.08)",
        border: "1px solid rgba(255,255,255,0.12)",
      }}
    >
      {children}
    </span>
  );
}

function ProgressBar({ value }) {
  const pct = Math.max(0, Math.min(100, Math.round((value ?? 0) * 100)));
  return (
    <div
      style={{
        height: "10px",
        borderRadius: "999px",
        background: "rgba(255,255,255,0.08)",
        border: "1px solid rgba(255,255,255,0.12)",
        overflow: "hidden",
      }}
      aria-label={`Confidence ${pct}%`}
      title={`Confidence ${pct}%`}
    >
      <div
        style={{
          height: "100%",
          width: `${pct}%`,
          background: "rgba(255,255,255,0.35)",
        }}
      />
    </div>
  );
}

function SectionCard({ section, isMissing }) {
  return (
    <div
      style={{
        borderRadius: "16px",
        padding: "1rem",
        background: "rgba(255,255,255,0.06)",
        border: "1px solid rgba(255,255,255,0.10)",
        boxShadow: "0 6px 18px rgba(0,0,0,0.18)",
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "baseline",
          justifyContent: "space-between",
          gap: "1rem",
          marginBottom: "0.75rem",
        }}
      >
        <div style={{ display: "flex", gap: "0.5rem", alignItems: "center" }}>
          <h3 style={{ margin: 0 }}>{section.name}</h3>
          <Badge>#{section.orderIndex}</Badge>
          {isMissing && <Badge>Missing</Badge>}
        </div>
        <div style={{ minWidth: "220px" }}>
          <div style={{ fontSize: "0.8rem", opacity: 0.85, marginBottom: 6 }}>
            Confidence: {Math.round((section.confidence ?? 0) * 100)}%
          </div>
          <ProgressBar value={section.confidence ?? 0} />
        </div>
      </div>

      <div
        style={{
          whiteSpace: "pre-wrap",
          lineHeight: 1.45,
          padding: "0.85rem",
          borderRadius: "12px",
          background: "rgba(0,0,0,0.25)",
          border: "1px solid rgba(255,255,255,0.08)",
          minHeight: "64px",
        }}
      >
        {section.content?.trim()
          ? section.content
          : "— (empty) add content by processing text —"}
      </div>
    </div>
  );
}

export default function App() {
  const [mode, setMode] = useState("text"); 

  const [templates, setTemplates] = useState([]);
  const [selectedTemplate, setSelectedTemplate] = useState("");
  const [rawText, setRawText] = useState("");
  const [result, setResult] = useState(null);

  const [error, setError] = useState(null);
  const [loadingTemplates, setLoadingTemplates] = useState(true);
  const [processing, setProcessing] = useState(false);

  useEffect(() => {
    setLoadingTemplates(true);
    fetchTemplates()
      .then((data) => setTemplates(data))
      .catch((err) => setError(err.message))
      .finally(() => setLoadingTemplates(false));
  }, []);

  const selectedTemplateObj = useMemo(() => {
    return templates.find((t) => t.key === selectedTemplate) || null;
  }, [templates, selectedTemplate]);

  async function handleSubmit(e) {
    e.preventDefault();
    setError(null);
    setResult(null);
    setProcessing(true);

    try {
      const data = await processText(selectedTemplate, rawText);
      data.sections = [...(data.sections || [])].sort(
        (a, b) => a.orderIndex - b.orderIndex
      );
      setResult(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setProcessing(false);
    }
  }

  const missingSet = useMemo(() => {
    const missing = result?.missingSections || [];
    return new Set(missing.map((m) => m.toLowerCase()));
  }, [result]);

  return (
    <div
      style={{
        minHeight: "100vh",
        padding: "2rem",
        background:
          "radial-gradient(1200px 700px at 20% 10%, rgba(120,120,255,0.18), transparent 55%), radial-gradient(900px 600px at 80% 20%, rgba(0,200,255,0.14), transparent 60%), #0b0f17",
        color: "rgba(255,255,255,0.92)",
      }}
    >
      <div style={{ maxWidth: "980px", margin: "0 auto" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            gap: "1rem",
            alignItems: "flex-end",
            marginBottom: "1.25rem",
          }}
        >
          <div>
            <h1 style={{ margin: 0, fontSize: "2rem" }}>Project Ordinate</h1>
            <div style={{ opacity: 0.8, marginTop: 6 }}>
              Turn raw thoughts into structured documents (private, human-in-the-loop).
            </div>
          </div>

          <div style={{ display: "flex", gap: "0.75rem" }}>
            <button
              onClick={() => setMode("text")}
              style={{
                padding: "0.55rem 0.9rem",
                borderRadius: "12px",
                border: "1px solid rgba(255,255,255,0.15)",
                background:
                  mode === "text"
                    ? "rgba(255,255,255,0.14)"
                    : "rgba(255,255,255,0.06)",
                color: "rgba(255,255,255,0.92)",
                cursor: "pointer",
              }}
            >
              Text Mode
            </button>
            <button
              onClick={() => setMode("voice")}
              style={{
                padding: "0.55rem 0.9rem",
                borderRadius: "12px",
                border: "1px solid rgba(255,255,255,0.15)",
                background:
                  mode === "voice"
                    ? "rgba(255,255,255,0.14)"
                    : "rgba(255,255,255,0.06)",
                color: "rgba(255,255,255,0.92)",
                cursor: "pointer",
              }}
            >
              Voice Mode
            </button>
          </div>
        </div>

        {mode === "voice" ? (
          <div
            style={{
              borderRadius: "18px",
              padding: "1rem",
              background: "rgba(255,255,255,0.06)",
              border: "1px solid rgba(255,255,255,0.10)",
            }}
          >
            <Voice />
          </div>
        ) : (
          <>
            {error && (
              <div
                style={{
                  padding: "0.85rem 1rem",
                  borderRadius: "14px",
                  background: "rgba(255,0,0,0.12)",
                  border: "1px solid rgba(255,0,0,0.22)",
                  marginBottom: "1rem",
                }}
              >
                {error}
              </div>
            )}

            <div
              style={{
                display: "grid",
                gridTemplateColumns: "1fr",
                gap: "1rem",
              }}
            >
              <div
                style={{
                  borderRadius: "18px",
                  padding: "1rem",
                  background: "rgba(255,255,255,0.06)",
                  border: "1px solid rgba(255,255,255,0.10)",
                }}
              >
                <form onSubmit={handleSubmit}>
                  <div
                    style={{
                      display: "grid",
                      gridTemplateColumns: "1fr 1fr",
                      gap: "1rem",
                      alignItems: "end",
                    }}
                  >
                    <div>
                      <div style={{ fontSize: "0.9rem", opacity: 0.85 }}>
                        Template
                      </div>
                      <select
                        value={selectedTemplate}
                        onChange={(e) => setSelectedTemplate(e.target.value)}
                        required
                        disabled={loadingTemplates}
                        style={{
                          width: "100%",
                          marginTop: 6,
                          padding: "0.6rem 0.7rem",
                          borderRadius: "12px",
                          border: "1px solid rgba(255,255,255,0.15)",
                          background: "rgba(0,0,0,0.25)",
                          color: "rgba(255,255,255,0.92)",
                        }}
                      >
                        <option value="">
                          {loadingTemplates ? "Loading..." : "-- select --"}
                        </option>
                        {templates.map((t) => (
                          <option key={t.key} value={t.key}>
                            {t.key}
                          </option>
                        ))}
                      </select>
                    </div>

                    <button
                      type="submit"
                      disabled={processing}
                      style={{
                        padding: "0.7rem 0.95rem",
                        borderRadius: "12px",
                        border: "1px solid rgba(255,255,255,0.15)",
                        background: "rgba(255,255,255,0.12)",
                        color: "rgba(255,255,255,0.92)",
                        cursor: "pointer",
                      }}
                    >
                      {processing ? "Processing..." : "Process"}
                    </button>
                  </div>

                  <div style={{ marginTop: "1rem" }}>
                    <div style={{ fontSize: "0.9rem", opacity: 0.85 }}>
                      Raw input
                    </div>
                    <textarea
                      rows={7}
                      value={rawText}
                      onChange={(e) => setRawText(e.target.value)}
                      required
                      placeholder="Paste rough notes here…"
                      style={{
                        width: "100%",
                        marginTop: 6,
                        padding: "0.75rem",
                        borderRadius: "14px",
                        border: "1px solid rgba(255,255,255,0.15)",
                        background: "rgba(0,0,0,0.25)",
                        color: "rgba(255,255,255,0.92)",
                        resize: "vertical",
                        lineHeight: 1.4,
                      }}
                    />
                  </div>

                  {selectedTemplateObj && (
                    <div style={{ marginTop: "0.75rem", opacity: 0.8 }}>
                      Sections:{" "}
                      {selectedTemplateObj.sections
                        ?.slice()
                        .sort((a, b) => a.orderIndex - b.orderIndex)
                        .map((s) => s.name)
                        .join(" • ")}
                    </div>
                  )}
                </form>
              </div>

              {result && (
                <div
                  style={{
                    borderRadius: "18px",
                    padding: "1rem",
                    background: "rgba(255,255,255,0.06)",
                    border: "1px solid rgba(255,255,255,0.10)",
                  }}
                >
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "space-between",
                      gap: "1rem",
                      alignItems: "center",
                      marginBottom: "1rem",
                    }}
                  >
                    <div>
                      <h2 style={{ margin: 0 }}>Structured Document</h2>
                      <div style={{ opacity: 0.8, marginTop: 4 }}>
                        Missing:{" "}
                        {result.missingSections?.length
                          ? result.missingSections.join(", ")
                          : "None"}
                      </div>
                    </div>
                    <Badge>{result.id}</Badge>
                  </div>

                  <div
                    style={{
                      display: "grid",
                      gridTemplateColumns: "1fr",
                      gap: "1rem",
                    }}
                  >
                    {result.sections?.map((s) => (
                      <SectionCard
                        key={s.id}
                        section={s}
                        isMissing={missingSet.has((s.name || "").toLowerCase())}
                      />
                    ))}
                  </div>
                </div>
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
}
