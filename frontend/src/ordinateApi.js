const API_BASE = "http://localhost:8080/api";

export async function fetchTemplates() {
  const res = await fetch(`${API_BASE}/templates`);
  if (!res.ok) {
    const text = await res.text().catch(() => "");
    throw new Error(`Failed to fetch templates (${res.status}): ${text}`);
  }
  return res.json();
}

export async function processText(templateKey, rawText) {
  const res = await fetch(`${API_BASE}/process/text`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ templateKey, rawText }),
  });

  if (!res.ok) {
    const data = await res.json().catch(() => null);
    const msg = data?.message || `Request failed (${res.status})`;
    throw new Error(msg);
  }

  return res.json();
}
