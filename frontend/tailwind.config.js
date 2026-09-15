/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        primary: '#1e6bb8',
        'primary-dark': '#155a9c',
        'primary-soft': '#e8f2fb',
        mint: '#2fbf8a',
        'mint-dark': '#1f9a6d',
        'mint-soft': '#e6f8f1',
        bg: '#f3f5f7',
        card: '#ffffff',
        ink: '#1e293b',
        muted: '#64748b',
        line: '#e2e8f0',
        danger: '#dc5a5a',
        'danger-soft': '#fdecec',
        warn: '#d97706',
        'warn-soft': '#fff7ed',
      },
    },
  },
  plugins: [],
}

