type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & { children?: React.ReactNode }

export default function Button(props: Props) {
  return (
    <button
      {...props}
      className={`bg-blue-600 text-white px-4 py-2 rounded ${props.className ?? ''}`}
    />
  )
}
